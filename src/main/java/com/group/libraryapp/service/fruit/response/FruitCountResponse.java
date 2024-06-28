
package com.group.libraryapp.service.fruit.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FruitCountResponse {
    private long count;

    @Builder
    private FruitCountResponse(long count) {
        this.count = count;
    }
}
