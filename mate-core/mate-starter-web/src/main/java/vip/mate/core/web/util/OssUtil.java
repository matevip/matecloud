package vip.mate.core.web.util;

import org.apache.commons.io.FilenameUtils;

/**
 * OSS处理工具类
 *
 * @author pangu
 */
public class OssUtil {

	public static final String IMAGES_STR = "png,jpg,jpeg,gif,tif,bmp";
	public static final String VIDEO_STR = "avi,wmv,mpeg,mp4,mov,flv,rm,rmvb,3gp";

	/**
	 * 根据文件名获取文件类型：1.图片　2.视频　3.其他
	 *
	 * @param fileName 文件名
	 * @return int
	 */
	public static int getFileType(String fileName) {
		String fileType = FilenameUtils.getExtension(fileName);
		assert fileType != null;
		int type = 3;
		if (IMAGES_STR.contains(fileType)) {
			type = 1;
		} else if (VIDEO_STR.contains(fileType)) {
			type = 2;
		}
		return type;
	}
}
