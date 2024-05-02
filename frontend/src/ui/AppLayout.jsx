import { Outlet } from "react-router-dom";
import styled from "styled-components";
import Sidebar from "./Sidebar";
import Header from "./Header";

const StyledDiv = styled.div`
  display: grid;
  grid-template-columns: 20rem 1fr;
  grid-template-rows: auto 1fr;
  height: 100vh;
  overflow: hidden;
  gap: 0;
  grid-template-areas:
    "sidebar header"
    "sidebar main";

  @media (max-width: 768px) {
    grid-template-columns: 1fr;
    grid-template-rows: auto 1fr;
    grid-template-areas:
      "header"
      "main";
  }
`;

function AppLayout() {
  return (
    <StyledDiv>
      <Header />
      <Sidebar />
      <Outlet />
    </StyledDiv>
  );
}

export default AppLayout;
