package cn.iruier.core.util;


import cn.iruier.core.vo.ResultVo;

public class ExecuteUtil {
    public static ResultVo getResult(int result, String msg) {
        if (result > 0) {
            return ResultVo.setSuccess(msg + "成功");
        } else {
            return ResultVo.setError(msg + "失败");
        }
    }
}
