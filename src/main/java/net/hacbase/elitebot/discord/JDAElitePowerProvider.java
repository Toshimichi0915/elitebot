package net.hacbase.elitebot.discord;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Role;
import net.hacbase.elitebot.save.EliteSimpleData;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class JDAElitePowerProvider implements ElitePowerProvider {

    private final JDA jda;
    private final Set<ElitePower> powers;

    public JDAElitePowerProvider(JDA jda, Collection<EliteSimpleData> data) {
        this.jda = jda;
        this.powers = new HashSet<>();
        for (EliteSimpleData element : data)
            addElitePower(element);
    }

    @Override
    public ElitePower getElitePowerByName(String name) {
        for (ElitePower power : powers) {
            if (power.getName().equals(name))
                return power;
        }
        return null;
    }

    @Override
    public ElitePower getElitePowerById(String id) {
        for (ElitePower power : powers) {
            if (power.getId().equals(id))
                return power;
        }
        return null;
    }

    @Override
    public Set<ElitePower> getElitePowers() {
        return new HashSet<>(powers);
    }

    @Override
    public boolean addElitePower(EliteSimpleData data) {
        Role role = jda.getRoleById(data.getId());
        if (role == null) return false;
        this.powers.add(new JDAElitePower(role.getName(), role.getId()));
        return true;
    }

    public static class JDAElitePower implements ElitePower {

        private final String name;
        private final String id;

        public JDAElitePower(String name, String id) {
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
