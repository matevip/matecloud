package vip.mate.component.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vip.mate.component.entity.SysAttachment;
import vip.mate.component.mapper.SysAttachmentMapper;
import vip.mate.component.service.ISysAttachmentService;
import vip.mate.core.common.constant.ComponentConstant;
import vip.mate.core.common.util.OssUtil;
import vip.mate.core.common.util.StringPool;
import vip.mate.core.common.util.StringUtil;
import vip.mate.core.database.entity.Search;
import vip.mate.core.oss.core.OssTemplate;
import vip.mate.core.oss.props.OssProperties;

import java.util.UUID;

/**
 * <p>
 * 附件表 服务实现类
 * </p>
 *
 * @author pangu
 * @since 2020-08-09
 */
@Service
@AllArgsConstructor
public class SysAttachmentServiceImpl extends ServiceImpl<SysAttachmentMapper, SysAttachment> implements ISysAttachmentService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final OssTemplate ossTemplate;

    @Override
    public IPage<SysAttachment> listPage(Page page, Search search) {
        LambdaQueryWrapper<SysAttachment> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtil.isNotBlank(search.getStartDate())) {
            queryWrapper.between(SysAttachment::getCreateTime, search.getStartDate(), search.getEndDate());
        }
        if (StringUtil.isNotBlank(search.getKeyword())) {
            queryWrapper.like(SysAttachment::getName, search.getKeyword());
            queryWrapper.or();
            queryWrapper.like(SysAttachment::getId, search.getKeyword());
        }
        return this.baseMapper.selectPage(page, queryWrapper);
    }

    /**
     * 上传文件
     * @param file 文件体
     * @return boolean
     */
    @Override
    public boolean upload(MultipartFile file) {

        OssProperties ossProperties = (OssProperties) redisTemplate.opsForValue().get(ComponentConstant.OSS_DEFAULT);
        String fileName = UUID.randomUUID().toString().replace("-", "")
                + StringPool.DOT + FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            //上传文件
            assert ossProperties != null;
            ossTemplate.putObject(ossProperties.getBucketName(), fileName, file.getInputStream(), file.getSize(), file.getContentType());
            //生成URL
            String url = "https://" + ossProperties.getCustomDomain() + StringPool.SLASH + fileName;
            //上传成功后记录入库
            this.attachmentLog(file, url);
        } catch (Exception e) {
            log.error("上传失败", e);
            return false;
        }
        return true;
    }

    /**
     * 将上传成功的文件记录入库
     * @param file　文件
     * @param url　返回的URL
     * @return boolean
     */
    public boolean attachmentLog(MultipartFile file, String url) {
        SysAttachment sysAttachment = new SysAttachment();
        String original = file.getOriginalFilename();
        String originalName = FilenameUtils.getName(original);
        sysAttachment.setName(originalName);
        sysAttachment.setUrl(url);
        sysAttachment.setSize(file.getSize());
        sysAttachment.setType(OssUtil.getFileType(original));
        return this.save(sysAttachment);
    }
}
