import {
    Box,
    chakra,
    Flex,
    SimpleGrid,
    Stat,
    StatLabel,
    StatNumber,
    useColorModeValue,
  } from '@chakra-ui/react';
  import { ReactNode } from 'react';
  import { GoLocation } from 'react-icons/go';
  import { Link as RouterLink ,useNavigate} from "react-router-dom";
  
  interface StatsCardProps {
    id:number,
    name: string;
    maxHumidity: number;
    minHumidity: number;
    maxTemperature:number
    minTemperature:number
    icon: ReactNode;
    nodes:any[]
  }
  function StatsCard(props: StatsCardProps) {
   // const { id,name, maxHumidity,minHumidity,maxTemperature,minTemperature, icon } = props;
    const data:StatsCardProps = props;
    const navigate = useNavigate();
    const to = `/dashboard/environment/${data.id}`;
    const toEnvironmentDetails=()=>{
      navigate(to,{state:{name:data.name, 
                        maxHumidity:data.maxHumidity,
                       minHumidity:data.minHumidity,
                       maxTemperature: data.maxTemperature,
                       minTemperature:data.minTemperature,
                       nodes:data.nodes
                       ,}});
      }
     return (
      <a onClick={()=>{toEnvironmentDetails()}}>
      <Stat
        px={{ base: 2, md: 4 }}
        py={'5'}
        shadow={'xl'}
        border={'1px solid'}
        borderColor={useColorModeValue('gray.800', 'gray.500')}
        rounded={'lg'}>
        <Flex justifyContent={'space-between'}>
          <Box pl={{ base: 2, md: 4 }}>
            <StatLabel  isTruncated  fontWeight={'bold'}>
              {data.name}
            </StatLabel>
            <StatNumber fontSize={'1xl'} fontWeight={'medium'} color={'red.500'}>
               min Temperature  : {data.minTemperature} c° 
            </StatNumber>
            <StatNumber fontSize={'1xl'} fontWeight={'medium'} color={'blue.500'}>
              max Temperature  : {data.maxTemperature} c° 
            </StatNumber>

            <StatNumber fontSize={'1xl'} fontWeight={'medium'} color={'red.400'}>
               min Humidity  : {data.minHumidity} % 
            </StatNumber>
            <StatNumber fontSize={'1xl'} fontWeight={'medium'} color={'blue.400'}>
              max Humidity  : {data.maxHumidity} % 
            </StatNumber>
          </Box>
          <Box
            my={'auto'}
            color={useColorModeValue('gray.800', 'gray.200')}
            alignContent={'center'}>
            {data.icon}
          </Box>
        </Flex>
      </Stat>
      </a>
    );
  }
  
  export default function BasicStatistics({data}) {
    return (
    
      <Box maxW="7xl" mx={'auto'} my={'6'} pt={1} px={{ base: 2, sm: 12, md: 17 }}>
   
        <SimpleGrid columns={{ base: 1, md: 3 }} spacing={{ base: 5, lg: 8 }}>
      
        {data.map(env => 
            <StatsCard
            
              key={env.id}
              id={env.id}
              name={env.name}
              maxHumidity ={env.maxHumidity}
              minHumidity ={env.minHumidity}
              maxTemperature={env.maxTemperature}
              minTemperature={env.minTemperature}
              nodes={env.nodes}
              icon={<GoLocation size={'3em'} />}
          />

          )}

        </SimpleGrid>
      </Box>


    );
  }