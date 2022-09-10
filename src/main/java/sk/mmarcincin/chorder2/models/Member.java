package sk.mmarcincin.chorder2.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Map;

@Document
public class Member {
    @Id
    private String id;
    @NotBlank
    private String nickname;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private Role Role;
    @Value("0")
    private Integer earnedXp;
    @Value("0")
    private Integer level;
    private Map<String, Integer> achievedTasks;
    private LocalDateTime createdAt;

    /*public Member(@JsonProperty("nickname") String nickname,
                  @JsonProperty("role") Role role,
                  @JsonProperty("earnedXp") Integer earnedXp,
                  @JsonProperty("level") Integer level,
                  @JsonProperty("achievedTasks") Map<String, Integer> achievedTasks) {
        this.nickname = nickname;
        this.role = role;
        this.earnedXp = earnedXp;
        this.level = level;
        this.achievedTasks = achievedTasks;
        this.createdAt = LocalDateTime.now();
    }*/
    public Member() {
        this.createdAt = LocalDateTime.now();
    }

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Role getRole() {
        return Role;
    }

    public void setRole(Role Role) {
        this.Role = Role;
    }

    public Integer getEarnedXp() {
        return earnedXp;
    }

    public void setEarnedXp(Integer xp) {
        this.earnedXp = xp;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Map<String, Integer> getAchievedTasks() {
        return achievedTasks;
    }

    public void setAchievedTasks(Map<String, Integer> achievedTasks) {
        this.achievedTasks = achievedTasks;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return "Nickname: "+nickname;

    }
}
