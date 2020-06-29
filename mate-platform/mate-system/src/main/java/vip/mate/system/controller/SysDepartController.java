package vip.mate.system.controller;


import lombok.AllArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import vip.mate.core.auth.annotation.EnableToken;
import vip.mate.core.common.api.Result;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.tree.ForestNodeMerger;
import vip.mate.core.web.tree.INode;
import vip.mate.system.entity.SysDepart;
import vip.mate.system.service.ISysDepartService;
import vip.mate.system.vo.SysDepartVO;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 组织机构表 前端控制器
 * </p>
 *
 * @author xuzf
 * @since 2020-06-28
 */

@RestController
@AllArgsConstructor
@RequestMapping("/sys-depart")
public class SysDepartController extends BaseController {

    private final ISysDepartService sysDepartService;

    @EnableToken
    @GetMapping("/list")
    public Result<?> list() {
        List<SysDepart> sysDepartList = sysDepartService.list();
        List<SysDepartVO> sysDepartVOS = sysDepartList.stream().map(sysDepart -> {
            SysDepartVO sysDepartVO = new SysDepartVO();
            BeanUtils.copyProperties(sysDepart, sysDepartVO);
            return sysDepartVO;
        }).collect(Collectors.toList());
        return Result.data(ForestNodeMerger.merge(sysDepartVOS));
    }

    @EnableToken
    @GetMapping("/tree")
    public Result<?> tree() {
        return Result.data(ForestNodeMerger.merge(sysDepartService.tree()));
    }

    @EnableToken
    @PostMapping("/saveOrUpdate")
    public Result<?> saveOrUpdate(@Valid @RequestBody SysDepart sysDepart) {
        if (sysDepartService.saveOrUpdate(sysDepart)) {
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }
}

