package vn.test.hub.core.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class PaginationInfo {
    private int limit;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("current_pages")
    private int currentPage;

    public static PaginationInfo of(Page<?> page) {
        return PaginationInfo.builder()
                .limit(page.getSize())
                .totalPages(page.getTotalPages())
                .currentPage(page.getNumber())
                .build();
    }


}
