import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import AppLayout from "./ui/AppLayout";
import Welcome from "./pages/Welcome";
import GlobalStyles from "./styles/GlobalStyles";
import Search from "./pages/Search";
import Bookings from "./pages/Bookings";
import Settings from "./pages/Settings";
import Login from "./pages/Login";

function App() {
  return (
    <>
      <GlobalStyles />
      <BrowserRouter>
        <Routes>
          <Route path="/Home" element={<Home />} />
          <Route path="/" element={<AppLayout />}>
            <Route index element={<Navigate to="/dashboard" />} />
            <Route path="dashboard" element={<Welcome />} />
            <Route path="search" element={<Search />} />
            <Route path="bookings" element={<Bookings />} />
            <Route path="settings" element={<Settings />} />
          </Route>
          <Route path="login" element={<Login />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
