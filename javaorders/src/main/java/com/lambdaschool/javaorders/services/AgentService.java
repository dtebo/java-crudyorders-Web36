package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Agent;

public interface AgentService {
    Agent findAgentByAgentcode(long id);
    Agent save(Agent agent);
}
