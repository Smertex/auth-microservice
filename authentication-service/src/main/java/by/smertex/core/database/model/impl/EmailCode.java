package by.smertex.core.database.model.impl;

import by.smertex.core.database.model.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmailCode implements Entity<String>, Serializable {

    @Id
    private String email;

    private String code;

    @Override
    public String getId() {
        return email;
    }
}
