package efub.SweetMeback.domain.post.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Meeting {
    OFFLINE("OFFLINE", "대면"),
    ONLINE("ONLINE", "비대면");

    private final String label;
    private final String detail;
}
