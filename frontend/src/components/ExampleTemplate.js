import React, { useState } from 'react';
import './app.css';
import logo from './logo.jpg';
import trashIcon from './trash.png';

function App() {
  const [activeTab, setActiveTab] = useState('all');
  const [buttons, setButtons] = useState({
    all: ['Добавить мероприятие', 'Добавить мероприятие', 'Добавить мероприятие'],
    my: ['Добавить мероприятие', 'Добавить мероприятие', 'Добавить мероприятие'],
    invites: ['Добавить мероприятие', 'Добавить мероприятие', 'Добавить мероприятие'],
  });

  const [isProfileMenuOpen, setIsProfileMenuOpen] = useState(false);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [newEventName, setNewEventName] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [phone, setPhone] = useState('');
  const [email, setEmail] = useState('');
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [editIndex, setEditIndex] = useState(null);
  const [editEventData, setEditEventData] = useState({
    name: '',
    startDate: '',
    startTime: '',
    endDate: '',
    endTime: '',
    finances: '',
  });
  
  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  const handleDeleteButton = (indexToDelete) => {
    setButtons({
      ...buttons,
      [activeTab]: buttons[activeTab].filter((_, index) => index !== indexToDelete),
    });
  };

  const toggleProfileMenu = () => {
    setIsProfileMenuOpen(!isProfileMenuOpen);
  };

  const handleLogin = () => {
    setIsAuthenticated(true);
  };

  const handleTabClick = (tab) => {
    setActiveTab(tab);
    setIsMenuOpen(false);
  };

  const handleOpenModal = () => {
    setIsModalOpen(true);
    setNewEventName('');
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  const handleSaveEvent = () => {
    if (newEventName.trim() !== '') {
      setButtons({
        ...buttons,
        [activeTab]: [...buttons[activeTab], newEventName],
      });
      handleCloseModal();
    }
  };

  const handleEditButton = (index) => {
    const eventToEdit = buttons.my[index].split(" | ");
    setEditEventData({
      name: eventToEdit[0] || '',
      startDate: eventToEdit[1] || '',
      startTime: eventToEdit[2] || '',
      endDate: eventToEdit[3] || '',
      endTime: eventToEdit[4] || '',
      finances: eventToEdit[5] || '',
    });
  
    setEditIndex(index);
    setIsEditModalOpen(true);
  };

  const handleSaveEdit = () => {
    if (editIndex !== null) {
      const updatedButtons = [...buttons.my];
      updatedButtons[editIndex] = `${editEventData.name} | ${editEventData.startDate} | ${editEventData.startTime} | ${editEventData.endDate} | ${editEventData.endTime} | ${editEventData.finances}`;
      
      setButtons({ ...buttons, my: updatedButtons });
      setIsEditModalOpen(false);
    }
  };

  if (!isAuthenticated) {
    return (
      <div className="login-container">
        <h1>Авторизация</h1>
        <div className="input-group">
          <label>Имя пользователя:</label>
          <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
        </div>
        <div className="input-group">
          <label>Пароль:</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
        </div>
        <button className="round-button login-button" onClick={handleLogin}>Войти</button>
      </div>
    );
  }

  return (
    <div className="app-container">
      <header className="app-header">
        <div className="logo">
          <img src={logo} alt="Logo" className="round-logo-image" />
        </div>
        <nav className="header-nav">
          <div className="menu-container">
            <button className="menu-button" onClick={toggleMenu}>Меню</button>
            <div className={`menu-items ${isMenuOpen ? 'open' : ''}`}>
              <button className="menu-item" onClick={() => handleTabClick('all')}>Все</button>
              <button className="menu-item" onClick={() => handleTabClick('my')}>Мои</button>
              <button className="menu-item" onClick={() => handleTabClick('invites')}>Приглашения</button>
            </div>
          </div>
        </nav>
        <div className="header-icons">
          <button className={`round-button icon-button ${isProfileMenuOpen ? 'active' : ''}`} onClick={toggleProfileMenu}>👤</button>
        </div>
      </header>

      <main className="app-content">
      <div className="button-list-container">
          <div className="scrollable-button-list">
            {buttons[activeTab].map((buttonText, index) => (
              <div key={index} className="button-item">
                <button className="round-button list-button">{buttonText}</button>

                {activeTab === 'my' && (
                  <button className="round-button edit-button" onClick={() => handleEditButton(index)}>
                    ✏️
                  </button>
                )}

                <button className="round-button delete-button" onClick={() => handleDeleteButton(index)}>
                  <img src={trashIcon} alt="Delete" className="trash-icon" />
                </button>
              </div>
            ))}
          </div>
          <div className="add-button">
            <button className="round-button icon-button" onClick={handleOpenModal}>+</button>
          </div>
        </div>
      </main>

      <div className={`profile-menu ${isProfileMenuOpen ? 'open' : ''}`}>
      <button className="round-button icon-button close-profile-button" onClick={toggleProfileMenu}>👤</button>
      <h2>Личный кабинет</h2>
      <div className="profile-fields">
        <div className="input-group">
          <label>Имя:</label>
          <input type="text" value={firstName} onChange={(e) => setFirstName(e.target.value)} className="profile-input" />
        </div>
        <div className="input-group">
          <label>Фамилия:</label>
          <input type="text" value={lastName} onChange={(e) => setLastName(e.target.value)} className="profile-input" />
        </div>
        <div className="input-group">
          <label>Номер телефона:</label>
          <input type="text" value={phone} onChange={(e) => setPhone(e.target.value)} className="profile-input" />
        </div>
        <div className="input-group">
          <label>Почта:</label>
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} className="profile-input" />
        </div>
      </div>
      <button className="save-button">Сохранить</button>
    </div>


    {isModalOpen && (
      <div className="modal-overlay">
        <div className="modal">
          <button className="close-button" onClick={handleCloseModal}>✖</button>
          <h2 className="modal-title">Добавить мероприятие</h2>
          <div className="modal-content">
            <label>Название мероприятия:</label>
            <input type="text" value={newEventName} onChange={(e) => setNewEventName(e.target.value)} className="modal-input" />
          </div>
          <button className="save-button" onClick={handleSaveEvent}>Сохранить</button>
        </div>
      </div>
    )}

{isEditModalOpen && (
  <div className="modal-overlay2">
    <div className="modal2">
      <button className="close-button2" onClick={() => setIsEditModalOpen(false)}>✖</button>
      <h2 className="modal-title2">Редактировать мероприятие</h2>
      <div className="modal-content2">
        <label>Название:</label>
        <input type="text" value={editEventData.name} onChange={(e) => setEditEventData({ ...editEventData, name: e.target.value })} className="modal-input" />

        <label>Дата начала:</label>
        <input type="date" value={editEventData.startDate} onChange={(e) => setEditEventData({ ...editEventData, startDate: e.target.value })} className="modal-input" />

        <label>Время начала:</label>
        <input type="time" value={editEventData.startTime} onChange={(e) => setEditEventData({ ...editEventData, startTime: e.target.value })} className="modal-input" />

        <label>Дата конца:</label>
        <input type="date" value={editEventData.endDate} onChange={(e) => setEditEventData({ ...editEventData, endDate: e.target.value })} className="modal-input" />

        <label>Время конца:</label>
        <input type="time" value={editEventData.endTime} onChange={(e) => setEditEventData({ ...editEventData, endTime: e.target.value })} className="modal-input" />

        <label>Финансы (р.):</label>
        <input type="number" value={editEventData.finances} onChange={(e) => setEditEventData({ ...editEventData, finances: e.target.value })} className="modal-input" />
      </div>
      <button className="save-button2" onClick={handleSaveEdit}>Сохранить</button>
    </div>
  </div>
)}
    </div>
  );
}

export default App;