package mff.study.belajar_spring_restfullapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagingResponse {

    private Integer page;

    private Integer size;

    private Integer current;

}
