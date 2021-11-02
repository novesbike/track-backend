package com.hexagonal.api.core.ports.inbound;

import com.hexagonal.api.core.domain.valueobjects.MyActivityHistory;

public interface GetActivityHistory {
  MyActivityHistory execute();
}
