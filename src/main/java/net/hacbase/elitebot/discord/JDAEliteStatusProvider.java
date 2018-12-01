package net.hacbase.elitebot.discord;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Role;
import net.hacbase.elitebot.save.EliteSimpleData;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class JDAEliteStatusProvider implements EliteStatusProvider {

    private final JDA jda;
    private final Set<EliteStatus> statuses;

    public JDAEliteStatusProvider(JDA jda, Collection<EliteSimpleData> data) {
        this.jda = jda;
        this.statuses = new HashSet<>();
        for (EliteSimpleData element : data)
            addEliteStatus(element);
    }

    @Override
    public EliteStatus getEliteStatusByName(String name) {
        for (EliteStatus status : statuses) {
            if (status.getName().equals(name))
                return status;
        }
        return null;
    }

    @Override
    public EliteStatus getEliteStatusById(String id) {
        for (EliteStatus status : statuses) {
            if (status.getId().equals(id))
                return status;
        }
        return null;
    }

    @Override
    public Set<EliteStatus> getEliteStatuses() {
        return new HashSet<>(statuses);
    }

    @Override
    public boolean addEliteStatus(EliteSimpleData data) {
        Role role = jda.getRoleById(data.getId());
        if (role == null) return false;
        statuses.add(new JDAEliteStatus(role.getName(), role.getId()));
        return true;
    }

    @Override
    public boolean removeEliteStatus(EliteStatus status) {
        return statuses.remove(status);
    }

    public static class JDAEliteStatus implements EliteStatus {

        private final String name;
        private final String id;

        public JDAEliteStatus(String name, String id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getId() {
            return id;
        }
    }
}
