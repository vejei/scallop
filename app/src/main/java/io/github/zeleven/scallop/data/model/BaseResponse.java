package io.github.zeleven.scallop.data.model;

import java.util.List;

public class BaseResponse<T> {
    private List<T> results;

    public List<T> getResults() {
        return results;
    }
}
