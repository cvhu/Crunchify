package org.cvhu;

import java.io.PrintWriter;
import java.util.List;

public interface CrunchParser {
    public List<TechCrunchPost> run();
    public void exportCsv(PrintWriter writer);
}
