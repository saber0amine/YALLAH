import styled from "styled-components";
import Heading from "../../ui/Heading";
import StarRating from "../../ui/StarRating";
import Button from "../../ui/Button";
const Card = styled.div`
  padding: 1rem;
  border-radius: 8px;
  background-color: #f0f0f0;
  img {
    width: 100%;
    border-radius: 8px;
  }
`;
const StyledP = styled.p`
  margin: 1.2rem 0;
  font-size: 1.2rem;
  font-weight: 500;
  color: var(--color-gray-700);
`;

const CardHeader = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
`;
function ActivityCard({ activity }) {
  return (
    <Card>
      <img src={activity.imageUrl} />
      <div>
        <CardHeader>
          <Heading as="h4">{activity.name}</Heading>
          <StarRating
            maxRating={5}
            defaultRating={activity.rating}
            color="black"
            size={15} // in pixels
          />
        </CardHeader>
        <StyledP>{activity.description}</StyledP>
        <Button>View details</Button>
      </div>
    </Card>
  );
}

export default ActivityCard;
