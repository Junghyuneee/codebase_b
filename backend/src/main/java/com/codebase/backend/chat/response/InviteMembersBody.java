package com.codebase.backend.chat.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InviteMembersBody {
    private List<String> members;
}
