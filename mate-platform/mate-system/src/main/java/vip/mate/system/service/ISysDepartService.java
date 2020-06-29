package vip.mate.system.service;

import vip.mate.system.entity.SysDepart;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.system.vo.SysDepartVO;

import java.util.List;

/**
 * <p>
 * 组织机构表 服务类
 * </p>
 *
 * @author xuzf
 * @since 2020-06-28
 */
public interface ISysDepartService extends IService<SysDepart> {

    List<SysDepartVO> tree();

}
