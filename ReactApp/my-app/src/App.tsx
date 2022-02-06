import React from 'react';
import './App.css';
import Login from './pages/Login'
import Registre from './pages/Registre'
import Dashboard from './pages/Dashboard'

import {BrowserRouter,Routes,Route} from 'react-router-dom';

function App() {
  return (
    <div className="App">
        <BrowserRouter>
          <Routes>
           <Route path="/" element={<Login />} />
            <Route path="/login" element={<Login />} />
            <Route path="/registre" element={<Registre />} />
            <Route path="/dashboard/*" element={<Dashboard />} />
          </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
