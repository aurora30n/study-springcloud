package cuner.sys.common.vo;

import cuner.sys.common.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVo extends User {

    public String confirmPwd;

}
