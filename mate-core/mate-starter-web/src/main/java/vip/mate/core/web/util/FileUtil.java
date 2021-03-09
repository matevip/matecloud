package vip.mate.core.web.util;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import vip.mate.core.common.constant.MateConstant;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * 文件处理工具类
 *
 * @author pangu
 */
@Slf4j
public class FileUtil {

	/**
	 * 获取文件类型
	 *
	 * @param file 文件
	 * @return 文件类型
	 * @throws Exception Exception
	 */
	private static String getFileType(File file) throws Exception {
		Preconditions.checkNotNull(file);
		if (file.isDirectory()) {
			throw new Exception("file不是文件");
		}
		String fileName = file.getName();
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	/**
	 * 校验文件类型是否是允许下载的类型
	 *
	 * @param fileType fileType
	 * @return Boolean
	 */
	private static Boolean fileTypeIsValid(String fileType) {
		Preconditions.checkNotNull(fileType);
		fileType = StringUtils.lowerCase(fileType);
		return ArrayUtils.contains(MateConstant.VALID_FILE_TYPE, fileType);
	}

	public static void download(String filePath, String fileName, Boolean delete, HttpServletResponse response) throws Exception {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new Exception("文件未找到");
		}

		String fileType = getFileType(file);
		if (!fileTypeIsValid(fileType)) {
			throw new Exception("暂不支持该类型文件下载");
		}
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "utf-8"));
		response.setContentType(MediaType.MULTIPART_FORM_DATA_VALUE);
		response.setCharacterEncoding("utf-8");
		try (InputStream inputStream = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
		} finally {
			if (delete) {
				delete(filePath);
			}
		}
	}

	/**
	 * 递归删除文件或目录
	 *
	 * @param filePath 文件或目录
	 */
	public static void delete(String filePath) {
		File file = new File(filePath);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				Arrays.stream(files).forEach(f -> delete(f.getPath()));
			}
		}
		file.delete();
	}

	public static ResponseEntity<FileSystemResource> export(File file) {
		if (file == null) {
			return null;
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new FileSystemResource(file));
	}
}
