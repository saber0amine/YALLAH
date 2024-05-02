import styled from "styled-components";
import Heading from "../ui/Heading";
import SuggestedActivities from "../ui/SuggestedActivities";

const Main = styled.main`
  background-color: var(--color-gray-50);
  padding: 4rem 4.8rem 6.4rem;
  overflow: scroll;
  height: 100vh;
  grid-area: main;
  //mobile
  @media (max-width: 768px) {
    padding: 1rem 2.4rem 6.4rem;
  }
`;
function Welcome() {
  return (
    <Main>
      <SuggestedActivities heading={"Popular Activities"} />
    </Main>
  );
}

export default Welcome;
