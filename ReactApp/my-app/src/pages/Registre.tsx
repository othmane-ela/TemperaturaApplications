import {
  Flex,
  Box,
  FormControl,
  FormLabel,
  Input,
  InputGroup,
  HStack,
  InputRightElement,
  Stack,
  Button,
  Heading,
  Text,
  Link,
} from '@chakra-ui/react';
import { useState ,SyntheticEvent} from 'react';
import { ViewIcon, ViewOffIcon } from '@chakra-ui/icons';
import Navbar from '../components/Navbar'
import {Navigate} from 'react-router-dom'

export default function Registre() {
  const [showPassword, setShowPassword] = useState(false);

  const [firstName,setFirstName] = useState('');
  const [lastName,setlastName] = useState('');
  const [username,setUsername] = useState('');
  const [password,setPassword] = useState('');
  const [redirect,setRedirect] = useState(false);


const submit = async (e:SyntheticEvent) =>{
    e.preventDefault();
    const response = await fetch('http://localhost:8080/api/v1/auth/signup',{  method:'POST',  headers:{'Content-Type':'application/json'}, body:JSON.stringify({
        firstName,
        lastName,
        username,
        password
    })
    });
    console.log(response);
    setRedirect(true)
}


  if(redirect)
  {
      return  <Navigate to="/login" />;
  }



  return (
      <>
      <Navbar/>
    <Flex
      minH={'100vh'}
      align={'center'}
      justify={'center'}
      bg={'gray.50'}>
       <form onSubmit={submit}>
      <Stack spacing={8} mx={'auto'} maxW={'lg'} py={12} px={6}>
        <Stack align={'center'}>
          <Heading fontSize={'4xl'} textAlign={'center'}>
            Sign up
          </Heading>
          <Text fontSize={'lg'} color={'gray.600'}>
            to enjoy all of our cool features ✌️
          </Text>
        </Stack>
        <Box
          rounded={'lg'}
          bg={'gray.80'}
          boxShadow={'lg'}
          p={8}>
          <Stack spacing={4}>
            <HStack>
              <Box>
                <FormControl id="firstName" isRequired>
                  <FormLabel>First Name</FormLabel>
                  <Input type="text" onChange={e => setFirstName(e.target.value)} />
                </FormControl>
              </Box>
              <Box>
                <FormControl id="lastName">
                  <FormLabel>Last Name</FormLabel>
                  <Input type="text"  onChange={e => setlastName(e.target.value)}/>
                </FormControl>
              </Box>
            </HStack>
            <FormControl id="username" isRequired>
              <FormLabel>Username</FormLabel>
              <Input type="text" onChange={e => setUsername(e.target.value)} />
            </FormControl>
            <FormControl id="password" isRequired>
              <FormLabel>Password</FormLabel>
              <InputGroup>
                <Input type={showPassword ? 'text' : 'password'}  onChange={e => setPassword(e.target.value)} />
                <InputRightElement h={'full'}>
                  <Button
                    variant={'ghost'}
                    onClick={() =>
                      setShowPassword((showPassword) => !showPassword)
                    }>
                    {showPassword ? <ViewIcon /> : <ViewOffIcon />}
                  </Button>
                </InputRightElement>
              </InputGroup>
            </FormControl>
            <Stack spacing={10} pt={2}>
              <Button
                type="submit"
                loadingText="Submitting"
                size="lg"
                bg={'cyan.400'}
                color={'white'}
                _hover={{
                  bg: 'cyan.500',
                }}>
                Sign up
              </Button>
            </Stack>
            <Stack pt={6}>
              <Text align={'center'}>
                Already a user? <Link color={'blue.400'}>Login</Link>
              </Text>
            </Stack>
          </Stack>
        </Box>
      </Stack>
      </form>
    </Flex>
    </>
  );
}