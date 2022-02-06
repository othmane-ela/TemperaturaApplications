import {
  Flex,
  Box,
  FormControl,
  FormLabel,
  Input,
  Checkbox,
  Stack,
  Link,
  Button,
  Heading,
  Text,
} from '@chakra-ui/react';
import Navbar from '../components/Navbar'
import {SyntheticEvent,useState} from 'react'
import {Navigate} from 'react-router-dom'

export default function Login() {

    const [username,setUsername] = useState('');
     const [password,setPassword] = useState('');
    const [redirect,setRedirect] = useState(false);

   const response  = null;
   
     const submit = async (e:SyntheticEvent) =>{
          e.preventDefault();
          const response = await fetch('http://localhost:8080/api/v1/auth/signin',{  method:'POST',  
          headers:{'Content-Type':'application/json'}, 
          body:JSON.stringify({
          username,
          password
    })
    });
  if(response.status === 200)
  {
    const responseData = await response.json();
    localStorage.setItem("key",responseData.token)
    setRedirect(true);
    console.log(redirect)
  }
}

if(redirect=== true)
{
      return  <Navigate to="/dashboard" />;
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
          <Heading fontSize={'4xl'}>Sign in to your account</Heading>
          <Text fontSize={'lg'} color={'gray.600'}>
            to enjoy all of our cool <Link color={'blue.400'}>features</Link> ✌️
          </Text>
        </Stack>
        <Box
          rounded={'lg'}
          bg={'gray.50'}
          boxShadow={'lg'}
          p={8}>
          <Stack spacing={4}>
            <FormControl id="username">
              <FormLabel>Username</FormLabel>
               <Input type="text" onChange={e => setUsername(e.target.value)} />
            </FormControl>
            <FormControl id="password">
              <FormLabel>Password</FormLabel>
            <Input type="password" onChange={e => setPassword(e.target.value)} />
            </FormControl>
            <Stack spacing={10}>
              <Stack
                direction={{ base: 'column', sm: 'row' }}
                align={'start'}
                justify={'space-between'}>
                <Checkbox>Remember me</Checkbox>
                <Link color={'blue.400'}>Forgot password?</Link>
              </Stack>
              <Button
                type="submit"
                bg={'cyan.400'}
                color={'white'}
                _hover={{
                  bg: 'cyan.500',
                }}>
                Sign in
              </Button>
            </Stack>
          </Stack>
        </Box>
      </Stack>
      </form>
    </Flex>
    </>
  );
 
}