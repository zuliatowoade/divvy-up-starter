import { useAuth0 } from "@auth0/auth0-react";

const LoginButton = () => {
    const { loginWithRedirect, isAuthenticated } = useAuth0();
    return (
        !isAuthenticated && (
            <div>
                <h1 style={{"margin-bottom": "10px"}}>Welcome to BillSplit</h1>
                <h2 style={{"margin-bottom": "10px"}}> Please sign in</h2>
            <button onClick={() =>loginWithRedirect() }>
                Sign In
            </button>
        </div>    
        )    
    )
}

export default LoginButton