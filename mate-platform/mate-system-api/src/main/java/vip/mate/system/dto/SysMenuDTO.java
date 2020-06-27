package vip.mate.system.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysMenuDTO implements Serializable {

    private static final long serialVersionUID = -7053157666510171528L;

    private Long id;

    private String label;
}
