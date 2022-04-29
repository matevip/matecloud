package vip.mate.code.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import vip.mate.code.controller.SysDataSourceController;
import vip.mate.code.service.ISysDataSourceService;
import vip.mate.core.common.constant.MateConstant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(controllers = {SysDataSourceController.class})
public class DataSourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISysDataSourceService sysDataSourceService;

    @Test
    void shouldReturnList() throws Exception {
        ResultActions perform = mockMvc.perform(get("/data-source/option-list")
                .contentType(MateConstant.JSON_UTF8));
        perform.andReturn().getResponse().setCharacterEncoding("UTF-8");
        perform.andDo(print()).andExpect(status().isOk());
    }
}
