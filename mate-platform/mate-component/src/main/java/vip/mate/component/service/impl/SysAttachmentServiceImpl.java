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

    @Override
    public boolean upload(MultipartFile file) {

        OssProperties ossProperties = (OssProperties) redisTemplate.opsForValue().get(ComponentConstant.OSS_DEFAULT);
        String fileName = UUID.randomUUID().toString().replace("-", "")
                + StringPool.DOT + FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            ossTemplate.putObject(ossProperties.getBucketName(), fileName, file.getInputStream(), file.getSize(), file.getContentType());

            SysAttachment sysAttachment = new SysAttachment();
            sysAttachment.setName(file.getOriginalFilename());
            sysAttachment.setUrl("https://" + ossProperties.getCustomDomain() + StringPool.SLASH + fileName);
            sysAttachment.setSize(file.getSize());
            sysAttachment.setType(1);
            this.save(sysAttachment);
        } catch (Exception e) {
            log.error("上传失败", e);
            return false;
        }
        return true;
    }
}
