package vip.mate.code.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.mate.code.mapper.TableMapper;
import vip.mate.code.vo.TableInfoVO;

@RequiredArgsConstructor
@DS("#dsName")
@Service
public class TableInfoService {

    private final TableMapper tableMapper;

    public IPage<TableInfoVO> queryPage(IPage page, String tableName, String dsName) {
        return tableMapper.selectByPage(page, tableName);
    }
}
