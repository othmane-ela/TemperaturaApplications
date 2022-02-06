import {
    Container,
    SimpleGrid,
    Image,
    Flex,
    Heading,
    Text,
    Stack,
    StackDivider,
    Icon,
    useColorModeValue,
  } from '@chakra-ui/react';
  import {
    IoAnalyticsSharp,
    IoLogoBitcoin,
    IoSearchSharp,
  } from 'react-icons/io5';
  import { ReactElement } from 'react';
  
  interface FeatureProps {
    text: string;
    iconBg: string;
    icon?: ReactElement;
  }
  
  const Feature = ({ text, icon, iconBg }: FeatureProps) => {
    return (
      <Stack direction={'row'} align={'center'}>
        <Flex
          w={8}
          h={8}
          align={'center'}
          justify={'center'}
          rounded={'full'}
          bg={iconBg}>
          {icon}
        </Flex>
        <Text fontWeight={600}>{text}</Text>
      </Stack>
    );
  };
  
  export default function EnvironmentBox({envData}) {
    return (
      <Container maxW={'5xl'} py={12}>
        <SimpleGrid columns={{ base: 1, md: 2 }} spacing={10}>
          <Stack spacing={4}>
            <Text
              textTransform={'uppercase'}
              color={'blue.400'}
              fontWeight={600}
              fontSize={'sm'}
              bg={useColorModeValue('blue.50', 'blue.900')}
              p={2}
              alignSelf={'flex-start'}
              rounded={'md'}>
              {envData.name}
            </Text>
            <Heading>{envData?.description}</Heading>
            <Text color={'gray.500'} fontSize={'lg'}>
              Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam
              nonumy eirmod tempor invidunt ut labore
            </Text>
          
              <Feature
                icon={
                  <Icon as={IoAnalyticsSharp} color={'red.500'} w={5} h={5} />
                }
                iconBg={useColorModeValue('red.100', 'red.900')}
                text={"Min Temperature : "+envData.minTemperature}
              />
              <Feature
                icon={
                    <Icon as={IoAnalyticsSharp} color={'green.500'} w={5} h={5} />
                  }
                iconBg={useColorModeValue('green.100', 'green.900')}
                text={"Max Temperature : "+envData.maxTemperature}
              />---
              <Feature
                icon={
                  <Icon as={IoAnalyticsSharp} color={'red.500'} w={5} h={5} />
                }
                iconBg={useColorModeValue('red.100', 'red.900')}
                text={"Min Humidity : "+envData.minHumidity}
              />
               <Feature
                icon={
                  <Icon as={IoAnalyticsSharp} color={'green.500'} w={5} h={5} />
                }
                iconBg={useColorModeValue('green.100', 'green.900')}
                text={"Max Humidity : "+envData.maxHumidity}
              />
            </Stack>
      
          <Flex>
            <Image
              rounded={'md'}
              alt={'feature image'}
              src={
                'https://webfiles2.luxweb.com/upload/mybusiness/articles/large/dabb4613d48c418b80b549d5e6b392de.jpg'
              }
              objectFit={'cover'}
            />
          </Flex>
        </SimpleGrid>
      </Container>
    );
  }