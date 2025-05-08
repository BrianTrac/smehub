package vn.test.hub.core.pagination;

import jakarta.persistence.EntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.test.hub.core.info.PaginationInfo;
import java.util.List;
import java.util.Map;

import static vn.test.hub.core.utils.PaginationUtils.filterValidSortColumns;
import static vn.test.hub.core.utils.PaginationUtils.toSort;

public class PaginationMapper {
    public static PaginationInfo mapToPaginationInfo(PaginationRequest paginationRequest, int totalPages) {
          return PaginationInfo.builder()
                .currentPage(paginationRequest.getPage())
                .limit(paginationRequest.getLimit())
                .totalPages(totalPages)
                .build();
    }

    public static Pageable mapToPageable(PaginationRequest paginationRequest, List<String> validColumns) {
        Map<String, String> sortParam = filterValidSortColumns(paginationRequest.getSort(), validColumns);
        return PageRequest.of(paginationRequest.getPage() - 1, paginationRequest.getLimit(), toSort(sortParam));
    }

}
