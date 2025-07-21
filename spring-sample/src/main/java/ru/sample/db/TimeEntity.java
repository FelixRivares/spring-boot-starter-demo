package ru.sample.db;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("time_table")
public class TimeEntity {

  @Id
  private Integer id;
  private String time;

}
