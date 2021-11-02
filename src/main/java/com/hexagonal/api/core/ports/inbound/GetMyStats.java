package com.hexagonal.api.core.ports.inbound;

import com.hexagonal.api.core.domain.valueobjects.ActivityStats;

public interface GetMyStats {
  ActivityStats execute();
}
