const tokenUrl = (tokenCode: string) => {
	const redirectUri = `http://localhost:3003/authorized&grant_type=authorization_code&code=${tokenCode}&code_verifier=qPsH306-ZDDaOE8DFzVn05TkN3ZZoVmI_6x4LsVglQI`;
	return `http://localhost:8080/oauth2/token?client_id=client&redirect_uri=${redirectUri}`;
}

export default tokenUrl;