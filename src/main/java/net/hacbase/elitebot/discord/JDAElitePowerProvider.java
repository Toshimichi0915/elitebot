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
        for(EliteSimpleData element : data) {
            Role role = jda.getRoleById(element.getId());
            if(role == null) continue;
            this.powers.add(new JDAElitePower(role.getName(), role.getId()));
        }
    }

    @Override
    public ElitePower getElitePowerByName(String name) {
        for(ElitePower power : powers) {
            if(power.getName().equals(name))
                return power;
        }
        return null;
    }

    @Override
    public ElitePower getElitePowerById(String id) {
        for(ElitePower power : powers) {
            if(power.getId().equals(id))
                return power;
        }
        return null;
    }

    @Override
    public Set<ElitePower> getElitePowers() {
        return new HashSet<>(powers);
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
