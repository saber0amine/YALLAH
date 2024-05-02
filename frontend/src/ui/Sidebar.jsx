import styled from "styled-components";
import Logo from "./Logo";
import UserMainNav from "./UserMainNav";

const StyledSidebar = styled.aside`
  background-color: var(--color-gray-0);
  padding: 3.2rem 2.4rem;
  border-right: 1px solid var(--color-gray-100);
  grid-row: 1/-1;
  display: flex;
  flex-direction: column;
  gap: 3.2rem;
  justify-content: space-between;
  height: 100vh;

  @media screen and (max-width: 768px) {
    display: none;
  }
`;

function Sidebar() {
  return (
    <StyledSidebar>
      <Logo />
      <UserMainNav />
    </StyledSidebar>
  );
}

export default Sidebar;
