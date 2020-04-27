package cuner.sys.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class User implements Serializable {
    private Long id;
    private String username;
    private String pwd;
    private String name;
}
