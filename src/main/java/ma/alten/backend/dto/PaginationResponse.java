package ma.alten.backend.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PaginationResponse(
        List<?> objects,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean last) {
}
