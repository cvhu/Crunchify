package org.cvhu;

import java.util.List;

public interface CrunchParser {
    public List<CrunchPost> parse();
    public void exportCsv();
}
