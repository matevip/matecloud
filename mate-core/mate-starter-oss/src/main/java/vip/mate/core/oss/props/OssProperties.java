/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package vip.mate.core.oss.props;

import lombok.Data;

import java.io.Serializable;

/**
 * AWS配置信息
 * @author lengleng
 * @author 858695266
 * <p>
 *     oss: enable: true
 *     endpoint: http://127.0.0.1:9000 #
 *     pathStyleAccess 采用nginx反向代理或者AWS S3 配置成true，支持第三方云存储配置成false
 *     pathStyleAccess: false
 *     access-key: mate
 *     secret-key: mate
 *     bucket-name: mate
 *     region: custom-domain: oss.mate.vip
 * </p>
 */
@Data
public class OssProperties implements Serializable {

    private static final long serialVersionUID = 7111600914195056135L;

    /**
     * 对象存储服务的URL
     */
    private String endpoint;

    /**
     * 自定义域名
     */
    private String customDomain;

    /**
     * true path-style nginx 反向代理和S3默认支持 pathStyle {http://endpoint/bucketname} false
     * supports virtual-hosted-style 阿里云等需要配置为 virtual-hosted-style
     * 模式{http://bucketname.endpoint}
     */
    private Boolean pathStyleAccess = true;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 区域
     */
    private String region;

    /**
     * Access key就像用户ID，可以唯一标识你的账户
     */
    private String accessKey;

    /**
     * Secret key是你账户的密码
     */
    private String secretKey;

    /**
     * 默认的存储桶名称
     */
    private String bucketName = "mate";

}
