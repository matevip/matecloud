package vip.mate.system.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuMeta implements Serializable {

    private static final long serialVersionUID = -1918771120674335058L;

    private String title;
    private String icon;
    private Boolean breadcrumb = true;
}
