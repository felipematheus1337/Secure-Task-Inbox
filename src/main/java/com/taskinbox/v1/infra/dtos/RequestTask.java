package com.taskinbox.v1.infra.dtos;

import java.util.List;

public record RequestTask(String ownerId, String title, String status, List<String> tags) {
}
