package net.hacbase.elitebot.discord;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.hacbase.elitebot.EliteBot;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JDAEliteUser implements EliteUser {

    private final EliteBot bot;
    private final Member member;

    public JDAEliteUser(EliteBot bot, Member member) {
        this.bot = bot;
        this.member = member;
    }

    @Override
    public ElitePower getElitePower() {
        List<Role> roles = member.getRoles();
        ElitePowerProvider prov = bot.getElitePowerProvider();
        for (Role role : roles) {
            ElitePower power = prov.getElitePowerById(role.getId());
            if (power != null)
                return power;
        }
        return null;
    }

    @Override
    public boolean setElitePower(ElitePower power) {
        ElitePower current = getElitePower();
        List<Role> roles = member.getRoles();
        if (power == null) {
            roles.removeIf(r -> r.getId().equals(current.getId()));
            member.getGuild().getController().modifyMemberRoles(member, roles.toArray(new Role[0]));
            return true;
        }

        if (current != null)
            roles.removeIf(r -> r.getId().equals(current.getId()));
        roles.add(bot.getJDA().getRoleById(power.getId()));

        member.getGuild().getController().modifyMemberRoles(member, roles.toArray(new Role[0])).queue();
        return true;
    }

    @Override
    public Collection<EliteStatus> getEliteStatuses() {
        Set<EliteStatus> result = new HashSet<>();
        List<Role> roles = member.getRoles();
        EliteStatusProvider prov = bot.getEliteStatusProvider();
        for (Role role : roles) {
            EliteStatus status = prov.getEliteStatusById(role.getId());
            if (status != null)
                result.add(status);
        }
        return result;
    }

    @Override
    public boolean setEliteStatuses(Collection<EliteStatus> statuses) {
        Set<Role> update = new HashSet<>();
        for (EliteStatus status : statuses) {
            Role role = bot.getJDA().getRoleById(status.getId());
            if (role == null) return false;
            update.add(role);
        }
        ElitePower power = getElitePower();
        if (power != null)
            update.add(bot.getJDA().getRoleById(power.getId()));
        member.getGuild().getController().modifyMemberRoles(member, update.toArray(new Role[0])).queue();
        return true;
    }

    @Override
    public void sendMessage(String message) {
        bot.getTextChannel().sendMessage(member.getAsMention() + " " + message).queue();
    }

    @Override
    public String getName() {
        return member.getUser().getName();
    }

    @Override
    public String getNickName() {
        return member.getEffectiveName();
    }

    @Override
    public String getId() {
        return member.getUser().getId();
    }

    @Override
    public boolean isAdmin() {
        return member.getRoles().contains(bot.getAdminRole());
    }
}
