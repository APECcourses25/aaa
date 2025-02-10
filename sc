import { useState } from 'react';
import { Card, CardContent } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';

const athletes = [
  { name: "Braden Andri", position: "Hooker", weight: 83.18, img: "https://img.freepik.com/premium-vector/vector-avatar-young-man-white-background_276184-243.jpg" },
  { name: "Andrew Logan", position: "Prop", weight: 113.18, img: "https://img.freepik.com/premium-vector/vector-avatar-young-man-white-background_276184-243.jpg" },
];

const performanceData = [
  { date: "Jan", bench1RM: 100, squat1RM: 150 },
  { date: "Feb", bench1RM: 105, squat1RM: 155 },
  { date: "Mar", bench1RM: 110, squat1RM: 160 },
];

export default function AthleteDashboard() {
  const [search, setSearch] = useState("");

  const filteredAthletes = athletes.filter(athlete =>
    athlete.name.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="p-4">
      <Input
        placeholder="Search athletes..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
        className="mb-4"
      />
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {filteredAthletes.map((athlete, index) => (
          <Card key={index} className="p-4">
            <CardContent className="flex items-center gap-4">
              <img src={athlete.img} alt={athlete.name} className="w-16 h-16 rounded-full" />
              <div>
                <h2 className="text-xl font-bold">{athlete.name}</h2>
                <p className="text-sm text-gray-500">{athlete.position}</p>
                <p className="text-sm">Weight: {athlete.weight} kg</p>
              </div>
            </CardContent>
          </Card>
        ))}
      </div>
      <div className="mt-8">
        <h2 className="text-xl font-bold mb-4">Performance Progress</h2>
        <ResponsiveContainer width="100%" height={300}>
          <LineChart data={performanceData}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="date" />
            <YAxis />
            <Tooltip />
            <Line type="monotone" dataKey="bench1RM" stroke="#8884d8" />
            <Line type="monotone" dataKey="squat1RM" stroke="#82ca9d" />
          </LineChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
}
