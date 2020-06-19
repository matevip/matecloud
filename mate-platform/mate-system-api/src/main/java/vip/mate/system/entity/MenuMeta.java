package vip.mate.system.entity;

import lombok.Data;

@Data
public class MenuMeta {
    private String title;
    private String icon;
    private Boolean breadcrumb = true;
}
