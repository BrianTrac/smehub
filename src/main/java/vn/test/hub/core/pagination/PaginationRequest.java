package vn.test.hub.core.pagination;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationRequest {
    private List<String> sort;
    @Min(1)
    private int page = 1;
    @Min(1)
    private int limit = 10;
}
