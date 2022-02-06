
import {
  IconButton,
  Avatar,
  Box,
  CloseButton,
  Flex,
  HStack,
  VStack,
  Icon,
  useColorModeValue,
  Link,
  Drawer,
  DrawerContent,
  Text,
  useDisclosure,
  BoxProps,
  FlexProps,
  Menu,
  MenuButton,
  MenuDivider,
  MenuItem,
  MenuList,
} from '@chakra-ui/react';
import {
  FiHome,
  FiTrendingUp,

  FiSettings,
  FiMenu,
  FiBell,
  FiChevronDown,
} from 'react-icons/fi';
import { IconType } from 'react-icons';
import { ReactText,useEffect,useState } from 'react';
import {Navigate} from 'react-router-dom'
import {BrowserRouter,Routes,Route} from 'react-router-dom';
import Environment from './Environment'
import Nodes from './Nodes'
import { Link as RouterLink } from "react-router-dom";
import EnvironmentDetails from './EnvironmentDetails';

interface LinkItemProps {
  name: string;
  icon: IconType;
  path:string;
}
const LinkItems: Array<LinkItemProps> = [
  { name: 'Environements',path:'/dashboard', icon: FiHome },
  { name: 'Nodes',path:'/dashboard/nodes', icon: FiTrendingUp },
  { name: 'Settings',path:'/dashboard/settings', icon: FiSettings },
];

export default function Dashboard() {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const [user,setUser] = useState(null);
  const [error,setError] = useState(null);
  const [connected,setConnected] = useState(false);

    useEffect(() => {
      fetch('http://localhost:8080/api/v1/auth/user',{
        headers:{
          'Content-Type':'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('key')
        }
      })
      .then(res => {
        if (res.ok) {
          return res.json();
        } else {
          throw new Error('Something went wrong');
        }
      })
      .then(data=> setUser(data))
      .catch(error => setError(error))
      
    },[connected])


      const signOut = (childData) =>{
        localStorage.removeItem('key');
        setConnected(childData);
  }

    if(error)
    {
      return  <Navigate to="/login" />;
    }
   
    
  

  return (
    <Box minH="100vh" bg={'gray.100'}>
      <SidebarContent
        onClose={() => onClose}
        display={{ base: 'none', md: 'block' }}
      />
     
      <Drawer
        autoFocus={false}
        isOpen={isOpen}
        placement="left"
        onClose={onClose}
        returnFocusOnClose={false}
        onOverlayClick={onClose}
        size="full">
        <DrawerContent>
          <SidebarContent onClose={onClose} />
        </DrawerContent>
      </Drawer>
      {/* mobilenav */}
      <MobileNav onOpen={onOpen} user={user} signOut={signOut} />
      <Box ml={{ base: 0, md: 60 }} p="4">
          <Routes>
             <Route path="/" element={<Environment />} />
             <Route path="/nodes" element={<Nodes />} />
             <Route  path="/environment/:envId" element={<EnvironmentDetails />} />
          </Routes>
      </Box>
    </Box>
  );
}

interface SidebarProps extends BoxProps {
  onClose: () => void;
}

const SidebarContent = ({ onClose, ...rest }: SidebarProps) => {
  return (
    <Box
      transition="3s ease"
      bg={'white'}
      borderRight="1px"
      borderRightColor={'gray.200'}
      w={{ base: 'full', md: 60 }}
      pos="fixed"
      h="full"
      {...rest}>
      <Flex h="20" alignItems="center" mx="8" justifyContent="space-between">
        <Text fontSize="2xl"  fontWeight="light">
          Temperatura
        </Text>
        <CloseButton display={{ base: 'flex', md: 'none' }} onClick={onClose} />
      </Flex>
      {LinkItems.map((link) => (
        <NavItem key={link.name} icon={link.icon} path={link.path}>
          {link.name}
        </NavItem>
      ))}
    </Box>
  );
};

interface NavItemProps extends FlexProps {
  icon: IconType;
  children: ReactText;
  path:string;
}


const NavItem = ({ icon,path, children, ...rest }: NavItemProps) => {
  return (

    <RouterLink to={path}>
      <Flex
        align="center"
        p="4"
        mx="4"
        borderRadius="lg"
        role="group"
        cursor="pointer"
        _hover={{
          bg: 'cyan.400',
          color: 'white',
        }}
        {...rest}>
        {icon && (
          <Icon
            mr="4"
            fontSize="16"
            _groupHover={{
              color: 'white',
            }}
            as={icon}
          />
        )}
        {children}
      </Flex>
      </RouterLink>
  );
};

interface MobileProps extends FlexProps {
  onOpen: () => void;
  user:User;
  signOut:(x:boolean) => void;
}

interface User {
  firstName: string,
  lastName: string,
  userName: string,
  roles: any[]
}


const MobileNav = ({ onOpen,user,signOut, ...rest }: MobileProps) => {


  const singOutHandle = (event) => {
    signOut(true);
    event.preventDefault();
}


  return (   
  
   <Flex
      ml={{ base: 0, md: 60 }}
      px={{ base: 4, md: 4 }}
      height="20"
      alignItems="center"
      bg={'white'}
      borderBottomWidth="1px"
      borderBottomColor={'gray.200'}
      justifyContent={{ base: 'space-between', md: 'flex-end' }}
      {...rest}>
      <IconButton
        display={{ base: 'flex', md: 'none' }}
        onClick={onOpen}
        variant="outline"
        aria-label="open menu"
        icon={<FiMenu />}
      />

      <Text
        display={{ base: 'flex', md: 'none' }}
        fontSize="2xl"
        fontFamily="monospace"
        fontWeight="bold">
        Temperatura
      </Text>

      <HStack spacing={{ base: '0', md: '6' }}>
        <IconButton
          size="lg"
          variant="ghost"
          aria-label="open menu"
          icon={<FiBell />}
        />
        <Flex alignItems={'center'}>
          <Menu>
            <MenuButton
              py={2}
              transition="all 0.3s"
              _focus={{ boxShadow: 'none' }}>
              <HStack>
                <Avatar
                  size={'sm'}
                  src={
                    'https://www.iop.org/sites/default/files/styles/original_optimised/public/2021-12/jose-ESA-001.jpg?itok=_KwXMi8I'
                  }
                />
                <VStack
                  display={{ base: 'none', md: 'flex' }}
                  alignItems="flex-start"
                  spacing="1px"
                  ml="2">
                  <Text fontSize="sm">
                    {user?.firstName}
                  </Text>
                  <Text fontSize="xs" color="gray.600">
                        {user?.roles?.map((role) => <span key={role.id}>{role.roleCode} - </span>)}
                  </Text>
                </VStack>
                <Box display={{ base: 'none', md: 'flex' }}>
                  <FiChevronDown />
                </Box>
              </HStack>
            </MenuButton>
            <MenuList
              bg={'white'}
              borderColor={'gray.200'}>
              <MenuItem>Profile</MenuItem>
              <MenuItem>Settings</MenuItem>
              <MenuDivider />
              <MenuItem onClick={singOutHandle}>Sign out</MenuItem>
            </MenuList>
          </Menu>
        </Flex>
      </HStack>
    </Flex>
  
 
  );
};