package sk.mmarcincin.chorder2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.mmarcincin.chorder2.models.Member;
import sk.mmarcincin.chorder2.repositories.MemberRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	MemberRepository memberRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(member);
	}

}
