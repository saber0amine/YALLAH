import styled from "styled-components";
import Heading from "./Heading";
import ActivityCard from "../features/activities/ActivityCard";
import { activities } from "../../data/data";
const StyledDiv = styled.div`
  padding: 2rem 0;
`;

const StyledList = styled.ul`
  list-style: none;
  padding: 0;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 2rem;
  //mobile
  @media (max-width: 768px) {
    grid-template-columns: 1fr;
  }
`;
function SuggestedActivities({ heading }) {
  console.log(activities);
  return (
    <StyledDiv>
      <Heading as="h3">{heading}</Heading>
      <StyledList>
        {activities.map((activity) => (
          <ActivityCard key={activity.id} activity={activity} />
        ))}
      </StyledList>
    </StyledDiv>
  );
}

export default SuggestedActivities;
