package by.smertex.core.database.model;

import java.io.Serializable;

public interface Entity<ID extends Serializable> {
    ID getId();
}
