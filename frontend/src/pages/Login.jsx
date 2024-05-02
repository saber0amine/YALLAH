import styled from "styled-components";
import Heading from "../ui/Heading";
import Button from "../ui/Button";

const Container = styled.div`
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  img {
    // image need to take the full width and height of the left side of the container
    width: 50vw;
    height: 100vh;
    object-fit: cover;
    border-top-left-radius: 10px;
    border-bottom-left-radius: 10px;
  }
`;

const LoginFormContainer = styled.div`
  // image need to take the full width and height of the right side of the container
  width: 50vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  // the form need to be centered and take 80% of  the width of the container
  form {
    width: 60%;
    display: flex;
    flex-direction: column;
    gap: 1rem;
    input {
      padding: 1rem 2rem;
      border-radius: 5px;
      border: 1px solid #ccc;
    }
  }
`;

const Styleddiv = styled.div`
  display: flex;
  align-items: center;
  gap: 0.5rem;
  justify-content: space-between;
  margin: 2rem 0;
`;

const StyledRememberMe = styled.div`
  display: flex;
  align-items: center;
  gap: 0.5rem;
  input {
    margin: 0;
  }
  label {
    margin: 0;
  }
`;

const StyledHeader = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 2rem;
`;
function Login() {
  return (
    <Container>
      <img src="loginPage.jpg" alt="" />
      <LoginFormContainer>
        <StyledHeader>
          <Heading as="h1">Log In</Heading>
          <Heading as="h4">Welcome back! Please login to your account.</Heading>
        </StyledHeader>
        <form>
          <label htmlFor="email">Your Email Address</label>
          <input
            type="email"
            id="email"
            name="email"
            placeholder="email@email.com"
          />
          <label htmlFor="password">Your password</label>
          <input
            type="password"
            id="password"
            name="password"
            placeholder="***********"
          />
          {/* add a remember me check and a forget password  in a styled componebt*/}
          <Styleddiv>
            <StyledRememberMe>
              <input type="checkbox" id="remember" name="remember" />
              <label htmlFor="remember">Remember me</label>
            </StyledRememberMe>
            <a href="#">Forgot password?</a>
          </Styleddiv>

          <Button type="submit">Login</Button>
        </form>
      </LoginFormContainer>
    </Container>
  );
}

export default Login;
