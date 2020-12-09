package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Agent;
import com.lambdaschool.javaorders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "agentService")
public class AgentServiceImpl implements AgentService {
    @Autowired
    AgentRepository agentRepository;

    @Override
    public Agent findAgentByAgentcode(long id){
        Agent agent = agentRepository.findAgentByAgentcode(id);

        if(agent == null){
            throw new EntityNotFoundException("Agent " + id + " Not Found!");
        }

        return agent;
    }

    @Transactional
    @Override
    public Agent save(Agent agent){
        return agentRepository.save(agent);
    }
}
