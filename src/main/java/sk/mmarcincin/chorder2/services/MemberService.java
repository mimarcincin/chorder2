package sk.mmarcincin.chorder2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.mmarcincin.chorder2.models.Member;
import sk.mmarcincin.chorder2.repositories.MemberRepository;

import java.util.List;
import java.util.Map;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public Member findMemberById(String id) {
        return memberRepository.findMemberById(id);
    }

    public Member addMember(Member newMember) {
        return memberRepository.save(newMember);
    }


    public void updateMember(String memberId, Member newMember) {
        newMember.setId(memberId);
        memberRepository.save(newMember);
    }

    public void addAchievedTask(String memberId, String taskId) {
        Member member = memberRepository.findMemberById(memberId);
        Map<String, Integer> tasks = member.getAchievedTasks();
        tasks.put(taskId, tasks.getOrDefault(taskId, 0)+1);
        member.setAchievedTasks(tasks);
        memberRepository.save(member);
    }

    public void deleteMemberById(String memberId) {
        memberRepository.deleteById(memberId);
    }

    public boolean existsByUsername(String username) {
        return memberRepository.existsByUsername(username);
    }
}

